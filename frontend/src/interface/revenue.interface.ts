import { RevenueSource } from "./revenueSource.interface";
import { RevenueType } from "./revenueType.interface";

export interface Revenue {
    id: string;
    idUser: string;
    revenueType: RevenueType;
    revenueSource: RevenueSource;
    dsRevenue: string;
    nuPayDay: number;
    ciIssuedInvoice: boolean;
    vlAmount: number;
    hrCreatedAt: Date;
    hrUpdatedAt: Date;
}