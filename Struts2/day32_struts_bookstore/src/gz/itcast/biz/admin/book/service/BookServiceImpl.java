package gz.itcast.biz.admin.book.service;

import gz.itcast.biz.admin.book.dao.BookDao;
import gz.itcast.biz.admin.book.dao.BookDaoImpl;
import gz.itcast.entity.Books;

public class BookServiceImpl implements BookService {
	BookDao dao = new BookDaoImpl();
	
	public void saveBook(Books book) {
		dao.saveBook(book);
	}

}
