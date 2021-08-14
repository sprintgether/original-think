import { HttpClient, HttpClientModule, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserComponent } from '../user/user.component';
import { RestResponse } from '../_model/rest-response';
import { User } from '../_model/user.model';


const httpsOptions = { headers: new HttpHeaders({ 'Content-Type' : 'application/json' }) };

@Injectable({ providedIn: 'root'})
export class AuthService {
    private url = environment.serverEndpoint + "/auth";

    constructor(private http: HttpClient){}

    // Connexion d'un utilisateur
    // loginRequest: login, password, 
    // RestResponse = data, message, code, status
    login(username: string, password: string): Observable<RestResponse>{
        return this.http.post<RestResponse>(this.url + "/login", {username, password}, httpsOptions)
    }

    // save a user
    // user: User
    register(email: string, password: string): Observable<RestResponse>{
        return this.http.post<RestResponse>(this.url + "/register", {email, password}, httpsOptions);
    }    

    // Lancement de la reinitialisation du mot de passe
    forgotPassword(email: string): Observable<RestResponse>{
        return this.http.post<RestResponse>(this.url + "/forgot/password", { email }, httpsOptions)
    }   
    
    // reinitialisation du mot de passe
    resetPassword(password: string, passwordConfirmation: string, tokenString: string): Observable<any> {
        return this.http.post<any>(this.url + "/reset/password", {password, passwordConfirmation, tokenString}, httpsOptions);
    }

    verifyEmail(token: string): Observable<RestResponse>{
        return this.http.post<RestResponse>(this.url + "/verify/email?token" + token, httpsOptions);
    }

    //Récupération de l'utilisateur coutant
    getCurrentUser(): Observable<RestResponse>{
        return this.http.get<RestResponse>(this.url + "/user/me", httpsOptions);
    }


































}