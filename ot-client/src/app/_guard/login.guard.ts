import { Injectable } from "@angular/core";
import { CanActivate, Router } from '@angular/router';
import { TokenStorageService } from '../_service/token-storage.service';

@Injectable()
export class LoginGuard implements CanActivate{

    constructor(private router: Router, private tokenStorageService: TokenStorageService) {}

    canActivate(): boolean {
        const accessToken = this.tokenStorageService.getAccessToken();
        if(!!accessToken)
            return true;
        else    
            this.router.navigate(['/auth']);    
    }
}