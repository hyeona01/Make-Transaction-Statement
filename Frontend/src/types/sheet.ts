export type sheetItem = {
  name: string;
  specification: string;
  quantity: number;
  unitPrice: number;
  amount: number;
  remark: string;
};

export type excelRequest = {
  transactionDate: string;
  clientName: string;
  businessNumber: string;
  representativeName: string;
  totalAmount: number;
  taxAmount: number;
  items: sheetItem[];
};
