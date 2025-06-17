export type SignupRequest = {
  username: string;
  password: string;
  companyName: string;
  businessStatus: string;
  businessCategory: string;
  email: string;
  businessNumber: string;
  address: string;
};

export type SignupResponse = {
  username: string;
  companyName: string;
  businessStatus: string;
  businessCategory: string;
  email: string;
  businessNumber: string;
  address: string;
};

export type SigninRequest = {
  email: string;
  password: string;
};

export type SigninResponse = {
  username: string;
  email: string;
};
