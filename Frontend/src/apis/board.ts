import { axiosWithAuth } from ".";
import type { boardRequest, boardResponse } from "../types/board";

export const getAllBoardData = async (): Promise<boardResponse[]> => {
  const response = await axiosWithAuth().get(`/board`);
  return response.data;
};

export const getBoardDetailData = async ({
  boardName,
  postId,
}: {
  boardName: string;
  postId: number;
}) => {
  const response = await axiosWithAuth().get(`/board/${boardName}/${postId}`);
  return response.data;
};

export const postBoard = async (data: boardRequest) => {
  const response = await axiosWithAuth().post(`/board`, data);
  return response.data;
};
