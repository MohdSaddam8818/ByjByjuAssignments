package com.byjuassignmsaddam.viewmodel.BaseViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byjuassignmsaddam.R
import com.byjuassignmsaddam.utility.EvaluationFailedException
import com.byjuassignmsaddam.utility.SingleLiveEvent


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import javax.net.ssl.HttpsURLConnection

open class BaseViewModel : ViewModel() {
/*
    public var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }*/

    private val errorLiveData: SingleLiveEvent<String> = SingleLiveEvent()
    private val internalErrorLiveData: SingleLiveEvent<Pair<Int, String?>> = SingleLiveEvent()
    private val toastLiveData: SingleLiveEvent<Pair<Int, String?>> = SingleLiveEvent()
    private val blockingLoaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val logoutDialogLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val loginScreenLiveData: MutableLiveData<Unit> = MutableLiveData()


    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getErrorObservable() = errorLiveData

    fun getInternalErrorObservable() = internalErrorLiveData

    fun getToastObservable() = toastLiveData

    fun getBlockingLoaderObservable() = blockingLoaderLiveData

    fun getLogoutObservable(): LiveData<Unit> = logoutDialogLiveData

    fun getLoginScreenObservable(): LiveData<Unit> = loginScreenLiveData




    fun handleApiError(throwable: Throwable) {
        //  RxJavaPlugins.onError(throwable)



        if (throwable is HttpException) {
            when (throwable.code()) {
                HttpsURLConnection.HTTP_FORBIDDEN -> {
                    /*  logOutUseCase?.let {
                          it.logout()
                          logoutDialogLiveData.postValue(Unit)
                      }*/
                }
                HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                    postError(R.string.something_went_wrong)

                HttpsURLConnection.HTTP_NOT_FOUND ->{
                    postError(R.string.something_went_wrong)
                }
                HttpsURLConnection.HTTP_CLIENT_TIMEOUT->{

                    postError(R.string.something_went_wrong)
                }
                else ->{
                    postError(R.string.something_went_wrong)
                }
            }
            return
        }
        /*if (throwable is ApiException) {
            val message = throwable.message
            if (!message.isNullOrEmpty())
                postError(message)

        }*/
        if (throwable is ConnectException) {
            postError(R.string.no_internet_connection)
        }





    }

    fun handleEvaluationException(throwable: Throwable): Boolean {
        if (throwable is EvaluationFailedException) {
            return if (!throwable.message.isNullOrBlank()) {
                postError(throwable.message)
                true
            } else if (throwable.extraText == null) {
                postError(throwable.messageResId)
                true
            } else {
                postError(throwable.messageResId, throwable.extraText)
                true
            }
        }
        return false
    }

    fun postError(messageResId: Int, extraText: String? = null) {
        internalErrorLiveData.postValue(Pair(messageResId, extraText))
    }

    fun postToast(messageResId: Int, extraText: String? = null) {
        toastLiveData.postValue(Pair(messageResId, extraText))
    }

    fun postError(message: String?) {
        errorLiveData.postValue(message)
    }

    fun setBlockingLoading(isLoading: Boolean) {
        blockingLoaderLiveData.postValue(isLoading)
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}