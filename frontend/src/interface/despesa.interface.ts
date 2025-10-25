export interface Despesa {
    id: string;
    date: Date;
    name: string;
    type: string;
    establishment: string;
    requisitor: string;
    amount: number;
}