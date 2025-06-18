import type { excelRequest } from "../types/sheet";
import { axiosWithAuth } from "./index";

export const generateExcel = async (data: excelRequest): Promise<Blob> => {
  const response = await axiosWithAuth().post(
    "/api/excel/transaction-statement",
    data,
    {
      responseType: "blob",
    }
  );
  return response.data;
};
