# encapsulated-rxjava
Encapsulated Rxjava in Android : The classes and methods of the Android Rxjava library are encapsulated. New methods have been added in order to facilitate use and increase code readability.


# encapsulated-rxjava
Encapsulated Rxjava in Android : The classes and methods of the Android Rxjava library are encapsulated. New methods have been added in order to facilitate use and increase code readability.


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.adilcetin:encapsulated-rxjava:0.1.0'
	}
  
  
  
Sample Usage of EncObservable on Thread:
    EncObservable<String> encObservable = EncObservable.create(emitter -> { // doing something... });
    
    encObservable.subscribeOnIOThread(bool->{ // doing something... }, throwable -> {});
    
    encObservable.subscribeOnMainThread(bool->{ // doing something... }, throwable -> {});
    
    encObservable.subscribeOnIOToMainThread(bool->{ // doing something... }, throwable -> {});
    
    encObservable.subscribeOnIOToMainThread(bool->{ // doing something... }, throwable -> {}, () -> {});
    
    
    
Sample Usage of EncObservable run(create and subscribe) on Thread:
 
    EncObservable.runOnIOThread(emitter -> { // doing something... }, throwable -> {});
    
    EncObservable.runOnMainThread(emitter -> { // doing something... }, throwable -> {});
    
    EncObservable.runOnIOToMainThread(emitter -> { // doing something... }, throwable -> {});
 
    EncObservableOnClass<Boolean> observableOnClass = new EncObservableOnClass<Boolean>() {
            @Override
            public EncObservableOnSubscribe<Boolean> source() {
                return emitter -> {   // doing something... };
            }

            @Override
            public void onNext(Boolean aBoolean) {
                // doing something...
            }

            @Override
            public void onError(Throwable throwable) {
                // doing something...
            }
        };
        
        EncObservable.runOnIOThread(observableOnClass);
        
        EncObservable.runOnMainThread(observableOnClass);
        
        EncObservable.runOnIOToMainThread(observableOnClass);



Sample Usage of EncObservable Cycle and Timer:
    
    EncObservable.runCycleInMinutes(5, bool->{ // doing something... }, throwable -> {});
    
    EncObservable.runCycleInSeconds(5, bool->{ // doing something... }, throwable -> {});
    
    EncObservable.runCycleInMillSeconds(5000, bool->{ // doing something... }, throwable -> {});
    
    
    EncObservable.runTimerInMinutes(5, bool->{ // doing something... }, throwable -> {});
    
    EncObservable.runTimerInSeconds(5000, bool->{ // doing something... }, throwable -> {});
 
 
 
 Sample Usage of EncComplatable:
    
    EncCompletable encCompletable = EncCompletable.create(emitter -> { // doing something... });
    
    encCompletable.subscribeOnIOThread(()->{ // doing something... }, throwable -> { // doing something... });
   
    encCompletable.subscribeOnMainThread(()->{ // doing something... }, throwable -> { // doing something... });
    
    encCompletable.subscribeOnIOToMainThread(()->{ // doing something... }, throwable -> { // doing something... });
        
    EncCompletableOnClass completableOnClass = new EncCompletableOnClass() {
            @Override
            public EncCompletableOnSubscribe source() {
                return emitter -> { // doing something... };
            }

            @Override
            public Encction onComplete() {
                return ()->{ // doing something... };
            }

            @Override
            public void onError(Throwable throwable) {
                // doing something...
            }
        };
        
        EncCompletable.runOnIOThread(completableOnClass);
        
        EncCompletable.runOnMainThread(completableOnClass);
        
        EncCompletable.runOnIOToMainThread(completableOnClass);



 Sample Usage of EncSingle:
    
    EncSingle<String> encSingle = EncSingle.create(emitter -> { // doing something... });
    
    encSingle.subscribeOnIOThread(bool->{ // doing something... }, throwable -> { // doing something... });
    
    encSingle.subscribeOnMainThread(bool->{ // doing something... }, throwable -> { // doing something... });
    
    encSingle.subscribeOnIOToMainThread(bool->{ // doing something... }, throwable -> { // doing something... });
    
   
    EncSingleOnClass<Boolean> singleOnClass = new EncSingleOnClass<Boolean>() {
            @Override
            public EncSingleOnSubscribe<Boolean> source() {
                return emitter -> { // doing something... };
            }

            @Override
            public void onError(Throwable throwable) {
                // doing something...
            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                // doing something...
            }
        };

        EncSingle.runOnIOThread(singleOnClass);
        
        EncSingle.runOnMainThread(singleOnClass);
        
        EncSingle.runOnIOToMainThread(singleOnClass);
 
 
 
 Sample Usage of EncPublishSubject:
      
       EncPublishSubject<String> encPublishSubject = EncPublishSubject.create();

       encPublishSubject.subscribeOnIOThread(bool->{  // doing something... }, throwable -> {  // doing something... });
       
       encPublishSubject.subscribeOnIOThread(bool->{  // doing something... }, throwable -> {  // doing something... }, () -> { // doing something... });
       
       encPublishSubject.subscribeOnMainThread(bool->{  // doing something... }, throwable -> {  // doing something... });
       
       encPublishSubject.subscribeOnMainThread(bool->{  // doing something... }, throwable -> {  // doing something... }, () -> { // doing something... });
       
       encPublishSubject.subscribeOnIOToMainThread(bool->{  // doing something... }, throwable -> {  // doing something... });
       
       encPublishSubject.subscribeOnIOToMainThread(bool->{  // doing something... }, throwable -> {  // doing something... }, () -> { // doing something... });
 
 
 
 Sample Usage of EncBehaviorSubject:
 
       EncBehaviorSubject<String> encBehaviorSubject = EncBehaviorSubject.create();

       encBehaviorSubject.subscribeOnIOThread(bool->{  // doing something... }, throwable -> {  // doing something... });
       
       encBehaviorSubject.subscribeOnIOThread(bool->{  // doing something... }, throwable -> {  // doing something... }, () -> { // doing something... });
       
       encBehaviorSubject.subscribeOnMainThread(bool->{  // doing something... }, throwable -> {  // doing something... });
       
       encBehaviorSubject.subscribeOnMainThread(bool->{  // doing something... }, throwable -> {  // doing something... }, () -> { // doing something... });
       
       encBehaviorSubject.subscribeOnIOToMainThread(bool->{  // doing something... }, throwable -> {  // doing something... });
       
       encBehaviorSubject.subscribeOnIOToMainThread(bool->{  // doing something... }, throwable -> {  // doing something... }, () -> { // doing something... });
 
 
 
 Sample Usage of EncDisposable and EncCompositeDisposable:
 
       EncDisposable encDisposable = EncSingle.runOnIOThread(singleOnClass);
       
       if(!encDisposable.isDisposed()) {
             encDisposable.dispose();
       }
       
 
       EncCompositeDisposable encCompositeDisposable = new EncCompositeDisposable();
       
       EncDisposable encDisposable1 = EncSingle.runOnIOThread(singleOnClass);
       
       EncDisposable encDisposable2 = EncSingle.runOnIOThread(singleOnClass);
       
       encCompositeDisposable.add(encDisposable1);
       encCompositeDisposable.add(encDisposable2);
       
       if(!encCompositeDisposable.isDisposed()) {
             encCompositeDisposable.dispose();
       }
       
       
  
 
 
 
 
 
 
  


