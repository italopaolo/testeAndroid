package desafio.com.br.desafioandroid.base;


import rx.subjects.PublishSubject;

public class BaseViewModel {

    protected PublishSubject<Throwable> errorSubject = PublishSubject.create();
}
