import { axiosWithoutAuth } from "./index";
import type {
  SigninRequest,
  SigninResponse,
  SignupRequest,
  SignupResponse,
} from "../types/auth";

export const signup = async (data: SignupRequest): Promise<SignupResponse> => {
  // const signupData = new FormData();

  // signupData.append("userSignupRequestDto", JSON.stringify(data));

  const response = await axiosWithoutAuth().post("/auth/signup", data);
  return response.data;
};

export const signin = async (data: SigninRequest): Promise<SigninResponse> => {
  const response = await axiosWithoutAuth().post("/auth/signin", data);
  return response.data;
};
