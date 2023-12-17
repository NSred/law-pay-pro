export interface RegistrationDto {
  merchantId: string;
  merchantPassword: string;
}

export interface LoginDto {
  merchantId: string;
  password: string;
}

export interface TokenState {
  accessToken: string;
  expiresIn: number;
}

export interface Merchant {
  id: number;
  merchantId: string;
}
