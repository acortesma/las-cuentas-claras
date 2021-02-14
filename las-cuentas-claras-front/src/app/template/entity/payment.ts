import { Friend } from "./friend";

export interface Payment {

    id: string;
    payer: Friend;
    amount: number;
    description: string;
    date: Date;
}