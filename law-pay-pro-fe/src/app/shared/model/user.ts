export interface User {
  id: number;
  name: string;
  username: string;
  email: string;
  role: Role;
}

export interface Role {
  id: string;
  name: string;
}
