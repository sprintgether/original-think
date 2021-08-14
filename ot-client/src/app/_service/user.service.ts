import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RestResponse } from '../_model/rest-response';

const httpOptions = { headers: new HttpHeaders({ 'Content-Type' : 'application/json'})}


@Injectable({
    providedIn: 'root'
})
export class UserService {
    private adminUrl = environment.serverEndpoint + "/admin";
    private userUrl = environment.serverEndpoint + "/user";

    constructor(private http: HttpClient){}

    // Récupèrer les informations sur l'utilisateur courant
    getCurrentUser(): Observable<RestResponse> {
        return this.http.get<RestResponse>(this.userUrl + "/me", httpOptions)
    }
}