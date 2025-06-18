import { axiosWithAuth } from ".";
import type { boardRequest, boardResponse } from "../types/board";

export const getAllBoardData = async (): Promise<boardResponse[]> => {
  const response = await axiosWithAuth().get(`/api/board`);
  return response.data;
};

export const getBoardData = async (id: number): Promise<boardResponse> => {
  const response = await axiosWithAuth().get(`/api/board/${id}`);
  return response.data;
};

export const postBoard = async (data: boardRequest) => {
  const response = await axiosWithAuth().post(`/api/board`, data);
  return response.data;
};
