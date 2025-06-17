import { axiosWithoutAuth } from "./index";
import type {
  SigninRequest,
  SigninResponse,
  SignupRequest,
  SignupResponse,
} from "../types/auth";
import { setAccessToken } from "../utils/auth";

export const signup = async (data: SignupRequest): Promise<SignupResponse> => {
  const response = await axiosWithoutAuth().post("/auth/signup", data);
  return response.data;
};

export const signin = async (data: SigninRequest): Promise<SigninResponse> => {
  const response = await axiosWithoutAuth().post("/auth/signin", data);

  const header = response.headers["authorization"];
  if (header && header.startsWith("Bearer ")) {
    const token = header.replace("Bearer ", "");
    console.log(response.data);

    setAccessToken(token);
  }

  return response.data;
};
