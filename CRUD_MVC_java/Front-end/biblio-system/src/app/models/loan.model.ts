import { last } from 'rxjs';
export interface loan{
    id_loan:number;
    fullName:String;
    bookTitle:String;
    status: String;
    loan_date: String;
    return_date:String
    // quantity:number;
}
export interface loanCreate{
    // id_loan:number;
    // id_loan:number;
    loan_date: String;
    return_date:String;
    status: String;
    
    
    
}