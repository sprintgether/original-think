import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Inject, Injectable } from "@angular/core";
import { TokenStorageService } from '../_service/token-storage.service';

const TOKEN_HEADER_KEY = "Authorization";

@Injectable({
    providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {
    constructor(private tokenStorageService: TokenStorageService){}

    intercept(request: HttpRequest<any>, next: HttpHandler){
        let authRequest = request;
        const accessToken = this.tokenStorageService.getAccessToken();
        if(accessToken != null){
            authRequest = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + accessToken)})
        }

        return next.handle(authRequest);
    }
}