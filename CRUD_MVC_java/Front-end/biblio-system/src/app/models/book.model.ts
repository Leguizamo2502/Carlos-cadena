import { categoryTraer } from "./category.model";

export interface book {
  id_book: number;
  title: string;
  description: string;
  publishers: [];
  authors: [];
  categorys:[]
}

export interface bookCreated {
  // id_book: number;
  title: string;
  description: string;
  publishers: [];
  authors: [];
  categorys:[]
}
