package mg.edena.shop.model.interf

interface ServiceCallback<T, E> {
    fun onFaillure(ex: E)
    fun onSucces(success: T)
}
