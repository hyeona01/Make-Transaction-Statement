import axios, { type AxiosInstance } from "axios";
import { getAccessToken } from "../utils/auth";

// 요청 인터셉터 설정 함수
function setInterceptors(instance: AxiosInstance): AxiosInstance {
  instance.interceptors.request.use(
    (config) => {
      const token = getAccessToken();
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    },
    (error) => Promise.reject(error)
  );
  return instance;
}

// Base URL 설정
const BASE_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";

// 인증 필요 인스턴스 생성
export const axiosWithAuth = (): AxiosInstance => {
  const instance = axios.create({ baseURL: BASE_URL });
  return setInterceptors(instance);
};

// 인증 없이 사용하는 인스턴스
export const axiosWithoutAuth = (): AxiosInstance => {
  return axios.create({ baseURL: BASE_URL });
};
