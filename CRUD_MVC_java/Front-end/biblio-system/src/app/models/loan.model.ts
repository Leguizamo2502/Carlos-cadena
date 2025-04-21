import { last } from 'rxjs';
export interface loan{
    id_loan:number;
    fullName:String;
    bookTitle:String;
    quantity:number;
}
export interface loanCreate{
    // id_loan:number;
    fullName:String;
    bookTitle:String;
    quantity:number;
}