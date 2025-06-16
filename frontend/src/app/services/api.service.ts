import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { IAllCattle, ICattle, IResumeCattle, ISoldCattle } from '../models/cattle.model';
import { IAllOwner, IOwner } from '../models/owner.model';
import { IAllSale, ISale } from '../models/sale.model';
import { IRoleList } from '../models/enum.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private _http = inject(HttpClient);
  private baseUrl: string = "http://localhost:8080/"

  //Cattles

  getAllCattles(): Observable<IAllCattle[]> {
    return this._http.get<IAllCattle[]>(`${this.baseUrl}cattle/all`);
  }

  getCattle(id: number): Observable<IAllCattle> {
    return this._http.get<IAllCattle>(`${this.baseUrl}cattle/${id}`);
  }

  getCattles(): Observable<IAllCattle[]> {
    return this._http.get<IAllCattle[]>(`${this.baseUrl}cattle/cattles`);
  }

  getResume(): Observable<IResumeCattle[]> {
    return this._http.get<IResumeCattle[]>(`${this.baseUrl}cattle/resume`);
  }

  getSold(): Observable<ISoldCattle[]> {
    return this._http.get<ISoldCattle[]>(`${this.baseUrl}cattle/sold`);
  }

  getNotAvailable(): Observable<IAllCattle[]> {
    return this._http.get<IAllCattle[]>(`${this.baseUrl}cattle/dead`);
  }

  /*addCattle(cattle: ICattle): void {
    this._http.post<ICattle>(`${this.baseUrl}cattle`, cattle).subscribe({
      next: (response) => {
        console.log("Registro creado correctamente");
      },
      error: (error) => {
        console.log("Error al crear el registro");
      }
    });
  }*/

  addCattle(cattle: ICattle): Observable<ICattle> {
    return this._http.post<ICattle>(`${this.baseUrl}cattle`, cattle);
  }
  

  deleteCattle(id: number): void {
    this._http.delete(`${this.baseUrl}cattle/delete/${id}`).subscribe({
      next: (response) => {
        console.log("Registro eliminado correctamente");
      },
      error: (error) => {
        console.log("Error al eliminar el registro");
      }
    });
  }
  
  updateCattle(cattle: ICattle): Observable<ICattle> {
    return this._http.put<ICattle>(`${this.baseUrl}cattle/update`, cattle);
  }

/*-----------------------------------------------------------------------------------------------*/
  //Owners

  getAllOwners(): Observable<IAllOwner[]> {
    return this._http.get<IAllOwner[]>(`${this.baseUrl}owner/all`);
  }

  getOwner(id: number): Observable<IAllOwner> {
    return this._http.get<IAllOwner>(`${this.baseUrl}owner/${id}`);
  }

  getOwners(): Observable<IAllOwner[]> {
    return this._http.get<IAllOwner[]>(`${this.baseUrl}owner`);
  }

  addOwner(owner: IOwner): Observable<IOwner> {
    return this._http.post<IOwner>(`${this.baseUrl}owner/save`, owner);
  }
  
  deleteOwner(id: number): void {
    this._http.delete(`${this.baseUrl}owner/${id}`).subscribe({
      next: (response) => {
        console.log("Registro eliminado correctamente");
      },
      error: (error) => {
        console.log("Error al eliminar el registro");
      }
    });
  }

  updateOwner(owner: IOwner): Observable<IOwner> {
    return this._http.put<IOwner>(`${this.baseUrl}owner`, owner);
  }

  /*---------------------------------------------------------------------------------------------*/
  //Sales

  getAllSales(): Observable<IAllSale[]> {
    return this._http.get<IAllSale[]>(`${this.baseUrl}sale/all`);
  }

  getSale(id: number): Observable<IAllSale> {
    return this._http.get<IAllSale>(`${this.baseUrl}sale/${id}`);
  }

  getSales(): Observable<IAllSale[]> {
    return this._http.get<IAllSale[]>(`${this.baseUrl}sale/sales`);
  }

  addSales(sale: ISale): void {
    this._http.post<ISale>(`${this.baseUrl}sale/save`, sale).subscribe({
      next: (response) => {
        console.log("Registro creado correctamente");
      },
      error: (error) => {
        console.log("Error al crear el registro");
      }
    })
  }

  deleteSale(id: number): void {
    this._http.delete(`${this.baseUrl}sale/${id}`).subscribe({
      next: (response) => {
        console.log("Registro eliminado correctamente");
      },
      error: (error) => {
        console.log("Error al eliminar el registro");
      }
    });
  }

  updateSale(sale: ISale): void {
    this._http.put(`${this.baseUrl}sale`, sale).subscribe({
      next: (response) => {
        console.log("Registro actualizado");
      },
      error: (error) => {
        console.log("Error al actualizar");
      }
    });
  }

/*---------------------------------------------------------------------------------------------*/
  //Enum  

  getRoles(): Observable<string[]> {
    return this._http.get<string[]>(`${this.baseUrl}enums/roles`);
  }
}
