export type SignupRequest = {
  name: string;
  password: string;
  companyName: string;
  businessStatus: string;
  businessCategory: string;
  email: string;
  businessNumber: string;
  address: string;
};

export type SignupResponse = {
  name: string;
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
