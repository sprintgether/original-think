import { HttpClient, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RestResponse } from '../_model/rest-response';
import { Think } from '../_model/think.model';

const httpsOptions = { headers: new HttpHeaders({ 'Content-Type' : 'application/json' }) };

@Injectable({ providedIn: 'root'})
export class ArticleService {
    private url = environment.serverEndpoint + "/article";   
    
    constructor(private http: HttpClient){}

    createThink(document: File, cover: File, think: Think ): Observable<HttpEvent<{}>> { 
        const formData : FormData = new FormData();
        formData.append('document', document);
        formData.append('cover', cover);
        formData.append('think', new Blob([JSON.stringify(think)], {
            type: "application/json"
        })
        );

        return this.http.post<HttpEvent<{}>>(this.url + "/think", formData, {
            reportProgress: true,
            observe: 'events',
        })
    } 
}