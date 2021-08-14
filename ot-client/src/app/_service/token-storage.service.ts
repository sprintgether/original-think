import { Injectable } from '@angular/core';
import { AuthResponse } from '../_model/auth-response';


const ACCESS_TOKEN_KEY = 'accessToken';
const REFRESH_TOKEN_KEY = 'refreshToken';
const TOKEN_TYPE_KEY = 'tokenType';
const EXPIRY_DURATION_KEY = "expiryDuration";
const USER_NAME_KEY = "username";
const AUTHORITIES_KEY = 'authorities';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
    constructor() {}

    private roles: Array<string> = [];

    public saveAccessToken(accessToken: string) {
        window.sessionStorage.removeItem(ACCESS_TOKEN_KEY);
        window.sessionStorage.setItem(ACCESS_TOKEN_KEY, accessToken);
    }

    public getAccessToken(): string {
        return sessionStorage.getItem(ACCESS_TOKEN_KEY);
    }
    

    public saveRefreshToken(refreshToken: string) {
        window.sessionStorage.removeItem(REFRESH_TOKEN_KEY);
        window.sessionStorage.setItem(REFRESH_TOKEN_KEY, refreshToken);
    }

    public getRefreshToken(): string {
        return sessionStorage.getItem(REFRESH_TOKEN_KEY);
      }

    public saveTokenType(tokenType: string) {
        window.sessionStorage.removeItem(TOKEN_TYPE_KEY);
        window.sessionStorage.setItem(TOKEN_TYPE_KEY, tokenType);
    }
    
    public getTokenType(): string {
        return sessionStorage.getItem(TOKEN_TYPE_KEY);
    }

    public saveExpiryDuration(expiryDuration: number) {
        window.sessionStorage.removeItem(EXPIRY_DURATION_KEY);
        window.sessionStorage.setItem(EXPIRY_DURATION_KEY, JSON.stringify(expiryDuration));
      }
    
      public getExpiryDuration(): number {
        return +(sessionStorage.getItem(EXPIRY_DURATION_KEY));
      }
    
      public saveUsername(username: string) {
        window.sessionStorage.removeItem(USER_NAME_KEY);
        window.sessionStorage.setItem(USER_NAME_KEY, username);
      }
    
      public getUsername(): string {
        return sessionStorage.getItem(USER_NAME_KEY);
      }


  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];

    if (sessionStorage.getItem(ACCESS_TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
        this.roles.push(authority.authority);
      });
    }
    return this.roles;
  } 

      // Sauvegarde de toute une réponse issue de l'établissement avec succès de la connexion
    public saveAuthResponse(authResponse: AuthResponse) {
        this.saveAccessToken(authResponse.accessToken);
        this.saveRefreshToken(authResponse.refresToken);
        this.saveTokenType(authResponse.tokenType);
        this.saveExpiryDuration(authResponse.expiryDuration);
        this.saveUsername(authResponse.username);
        this.saveAuthorities(authResponse.authorities);
    }


}    