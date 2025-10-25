import { FonteReceita } from "./fonteReceita.interface";
import { TipoReceita } from "./tipoReceita.interface";

export interface Receita {
    id: string;
    idUser: string;
    tipoReceita: TipoReceita;
    fonteReceita: FonteReceita;
    dsReceita: string;
    nuDiaFaturamento: number;
    ciNotaFiscal: boolean;
    vlReceita: number;
    hrCreatedAt: Date;
    hrUpdatedAt: Date;
}