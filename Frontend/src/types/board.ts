export type boardRequest = {
  title: string;
  contents: string;
};

export type boardResponse = {
  id: number;
  writer: string;
  title: string;
  contents: string;
  createdDate: string;
  updatedDate: string;
};
