import { axiosWithoutAuth } from "./index";
import type { SigninRequest, SignupRequest } from "../types/auth";

export const signup = async (data: SignupRequest, image?: File) => {
  const signupData = new FormData();

  signupData.append("userSignupRequestDto", JSON.stringify(data));

  if (image) {
    signupData.append("image", image);
  }

  const response = await axiosWithoutAuth().post("/auth/signup", signupData);
  return response.data;
};

export const signin = async (data: SigninRequest) => {
  const response = await axiosWithoutAuth().post("/auth/login", data);
  return response.data;
};
