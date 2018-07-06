# 토이 프로젝트

- ### 설명

  - Kotlin, Dagger2, Rx, MVP 등을 이용한 토이 프로젝트를 입니다.

  - 문제풀이 화면의 이미지는 백터 이미지 이고 계산기에 사용된 이미지는 xxhdpi입니다. 이미지와 음성 모두 제가 직접 만들었습니다.

  - 실행 시키시려면 서버를 작동 시키고 RetrofitManager에 서버 주소를 입력 하셔야 하며 서버의 주소에 따라 /cal을 붙여 주시거 떼어 주시면됩니다.

  - Db는  `idx` INT(11) NOT NULL AUTOINCREAMENT,
  `lessonNum` INT(11) NOT NULL, `lessonName` VARCHAR(255)  NOT NULL,
 `problemNumber` INT(11) NOT NULL, `problemContent` VARCHAR(255)  NOT NULL, `problemAnswer` VARCHAR(255)  NOT NULL,
 PRIMARY KEY (`idx`)); 로 구성 되어있습니다.


- ### 핵심 코드 설명

```
- CalculatorActivity

fun init() {

        calculatorPresenter.setView(this)
        var resourceId: Int
        resourceids = IntArray(10)
        for (i in 0..9) {
            resourceId = resources.getIdentifier("num_$i", "id", packageName)
            findViewById<View>(resourceId).tag = i  //태그 값을 부여합니다.
            resourceId = resources.getIdentifier("num$i", "raw", packageName)
            resourceids!![i] = resourceId
        }
        resouceCal = resources.getIdentifier("cal", "raw", packageName)
        music.calSongStart(application, resouceCal)

        clearNum()

        event = RxTextView.textChanges(main_score)
        calculatorPresenter.rxCalculate(event!!) // 모든 데이터는 presenter에서 처리하여야 하기 때문에 rxtextview를 presenter로 넘겨줍니다.

    }


@OnClick(R.id.plus_button, R.id.minus_button, R.id.multiply_button, R.id.division_button)
			//(0~9같은 숫자버튼들과, +-등의 기호버튼들 , clear, result등의 기능 버튼들을 기능에 따라 묶어
			// 각각 메소드 하나로 표현하였습니다.(여기선 +-등의 심볼)
    fun btnSymbol(v: View) {
        calculatorPresenter.btnSymbol(v.id)  //tag값을 presenter에 넘겨줍니다.
    }
//넘겨진 tag값의 처리는 MVVM버전과 같습니다.
```

```
- CalculatorPresenter

override fun rxCalculate(event: Observable<CharSequence>) {
        val disposable = event.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .filter { text->text.length>0 }.filter { text->
                    text.toString()[text.length-1] == '+' || text.toString()[text.length-1] == '-' || text.toString()[text.length-1] == 'X' ||
                            text.toString()[text.length-1] == '/'
                }
                .map<String> { text->  calc.calculate(text.toString().substring(0,text.length-1)) }
                .subscribe { data->calculatorView?.setRxResult(data)}
        compositeDisposable.add(disposable)
    }
//Activity로 부터 받은 rxtextview를 이용해 text의 마지막 문자가 +-X/일 경우 계산을 하여

//Activity의 값을 넘겨주어  rx_text 텍스트뷰에 계산값을 표시합니다.
```

```
- ProblemSolvingActivity

//엑티비티가 stop되거나 destory 되었을 때는 clear, onCreate나 onRestart되었을때는 구독
override fun onDestroy() {
        super.onDestroy()
        problemPresenter.unsubscribe() //clear
        music.mainsongStop()
    } 
override fun onRestart() {
        super.onRestart()
        problemPresenter.rxChange(event!!) //구독
        music.mainsongStart(this,resouceMain)
    }

override fun onStop() {
        super.onStop()
        problemPresenter.unsubscribe() //clear
        music.mainsongStop()
    }

override fun unsubscribe() {
        compositeDisposable.clear()
    }

// 주입된 CompositeDisposable(dagger2의 scope를 이용하여 어디에 주입하든 같은 객체값을 가집니다.(singleton과 같음))
// 클리어 해줍니다.(ProblemSolvingActivity의 경우 destroy 될때는 앱이 완전 죽어 따로 clear해줄 필요가 없지만 학습 목적 이므로 clear해주었습니다.)

```


