import axios from "axios";
import type { User } from "../types/user/user";
import type { LoginRequest } from "../types/user/loginRequest";
import type { RegisterUser } from "../types/user/registerUser";

import { ENV } from "../config/env";

const API_URL = ENV.PUBLIC_API_URL;

export const LoginData = async (loginData: LoginRequest): Promise<User> => {
  const response = await axios.post<User>(`${API_URL}/auth/login`, loginData);

  return response.data;
};

export const RegisterData = async (
  registerData: RegisterUser
): Promise<User> => {
  const response = await axios.post<User>(`${API_URL}/users`, registerData);

  return response.data;
};
