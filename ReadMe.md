# ���� ������Ʈ

- ### ����

  - Kotlin, Dagger2, Rx, MVP ���� �̿��� ���� ������Ʈ�� �Դϴ�.

  - ����Ǯ�� ȭ���� �̹����� ���� �̹��� �̰� ���⿡ ���� �̹����� xxhdpi�Դϴ�. �̹����� ���� ��� ���� ���� ��������ϴ�.

  - ���� ��Ű�÷��� ������ �۵� ��Ű�� RetrofitManager�� ���� �ּҸ� �Է� �ϼž� �ϸ� ������ �ּҿ� ���� /cal�� �ٿ� �ֽð� ���� �ֽø�˴ϴ�.

  - Db��  `idx` INT(11) NOT NULL AUTOINCREAMENT,
  `lessonNum` INT(11) NOT NULL, `lessonName` VARCHAR(255)  NOT NULL,
 `problemNumber` INT(11) NOT NULL, `problemContent` VARCHAR(255)  NOT NULL, `problemAnswer` VARCHAR(255)  NOT NULL,
 PRIMARY KEY (`idx`)); �� ���� �Ǿ��ֽ��ϴ�.


- ### �ٽ� �ڵ� ����

```
- CalculatorActivity

fun init() {

        calculatorPresenter.setView(this)
        var resourceId: Int
        resourceids = IntArray(10)
        for (i in 0..9) {
            resourceId = resources.getIdentifier("num_$i", "id", packageName)
            findViewById<View>(resourceId).tag = i  //�±� ���� �ο��մϴ�.
            resourceId = resources.getIdentifier("num$i", "raw", packageName)
            resourceids!![i] = resourceId
        }
        resouceCal = resources.getIdentifier("cal", "raw", packageName)
        music.calSongStart(application, resouceCal)

        clearNum()

        event = RxTextView.textChanges(main_score)
        calculatorPresenter.rxCalculate(event!!) // ��� �����ʹ� presenter���� ó���Ͽ��� �ϱ� ������ rxtextview�� presenter�� �Ѱ��ݴϴ�.

    }


@OnClick(R.id.plus_button, R.id.minus_button, R.id.multiply_button, R.id.division_button)
			//(0~9���� ���ڹ�ư���, +-���� ��ȣ��ư�� , clear, result���� ��� ��ư���� ��ɿ� ���� ����
			// ���� �޼ҵ� �ϳ��� ǥ���Ͽ����ϴ�.(���⼱ +-���� �ɺ�)
    fun btnSymbol(v: View) {
        calculatorPresenter.btnSymbol(v.id)  //tag���� presenter�� �Ѱ��ݴϴ�.
    }
//�Ѱ��� tag���� ó���� MVVM������ �����ϴ�.
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
//Activity�� ���� ���� rxtextview�� �̿��� text�� ������ ���ڰ� +-X/�� ��� ����� �Ͽ�

//Activity�� ���� �Ѱ��־�  rx_text �ؽ�Ʈ�信 ��갪�� ǥ���մϴ�.
```

```
- ProblemSolvingActivity

//��Ƽ��Ƽ�� stop�ǰų� destory �Ǿ��� ���� clear, onCreate�� onRestart�Ǿ������� ����
override fun onDestroy() {
        super.onDestroy()
        problemPresenter.unsubscribe() //clear
        music.mainsongStop()
    } 
override fun onRestart() {
        super.onRestart()
        problemPresenter.rxChange(event!!) //����
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

// ���Ե� CompositeDisposable(dagger2�� scope�� �̿��Ͽ� ��� �����ϵ� ���� ��ü���� �����ϴ�.(singleton�� ����))
// Ŭ���� ���ݴϴ�.(ProblemSolvingActivity�� ��� destroy �ɶ��� ���� ���� �׾� ���� clear���� �ʿ䰡 ������ �н� ���� �̹Ƿ� clear���־����ϴ�.)

```


