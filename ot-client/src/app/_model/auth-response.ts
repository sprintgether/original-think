export class AuthResponse {
    constructor(
      public accessToken: string,
      public refresToken: string,
      public tokenType: string,
      public expiryDuration: number,
      public username: string,
      public authorities: string []
    ) {}
  }
