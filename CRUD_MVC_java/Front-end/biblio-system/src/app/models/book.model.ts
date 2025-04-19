export interface book {
  id_book: number;
  title: string;
  description: string;
  book_author: {
    id_book_author: number;
    author: { id_author: number; first_name: string; last_name: string };
  }[];
  book_category: {
    id_book_category: number;
    category: { id_category: number; name: string };
  }[];
  book_publisher: {
    id_book_publisher: number;
    publisher: { id_publisher: number; name: string };
  }[];
}
