package com.fdmgroup.bookstore.data;

public interface BookRepository<T> extends Removeable<T>, Persistable<T>, Searchable<T> {

}
