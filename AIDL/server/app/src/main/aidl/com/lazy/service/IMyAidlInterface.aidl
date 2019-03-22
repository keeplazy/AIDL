// IMyAidlInterface.aidl
package com.lazy.service;
import com.lazy.service.Books;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    List<Books> getBookList();
    void addBookOut(out Books book);
    void addBookIn(in Books book);
    void addBookInOut(inout Books book);
}
