import type { clientRequest, clientResponse } from "../types/client";
import { axiosWithAuth } from "./index";

export const postClient = async (data: clientRequest) => {
  const response = await axiosWithAuth().post(`/api/clients`, data);
  return response.data;
};

export const getAllClient = async (): Promise<clientResponse[]> => {
  const response = await axiosWithAuth().get(`/api/clients`);
  return response.data;
};

export const getClient = async (id: number): Promise<clientResponse> => {
  const response = await axiosWithAuth().get(`/api/clients/${id}`);
  return response.data;
};
