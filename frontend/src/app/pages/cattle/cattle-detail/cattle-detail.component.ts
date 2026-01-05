import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../../services/api.service';
import { IAllCattle } from '../../../models/cattle.model';
import { LoadingComponent } from '../../../loading/loading.component';
import { CommonModule } from '@angular/common';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-cattle-detail',
  standalone: true,
  imports: [LoadingComponent, CommonModule],
  templateUrl: './cattle-detail.component.html',
  styleUrl: './cattle-detail.component.css'
})
export class CattleDetailComponent implements OnInit {

  loading: boolean = true;
  public cattle?: IAllCattle;

  private _route = inject(ActivatedRoute);
  private _router = inject(Router);
  private _apiService = inject(ApiService);

  ngOnInit(): void {
    this._route.params.pipe(
      switchMap(params => {
        this.loading = true; // Reset loading on params change
        return this._apiService.getCattle(params['id']);
      })
    ).subscribe({
      next: (data: IAllCattle) => {
        this.cattle = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading cattle details', err);
        this.loading = false;
        // Optional: Redirect back or show error message
        this.goBack();
      }
    });
  }

  goBack(): void {
    this._router.navigate(['/']); // Or go to '/cattles' or previous page. defaulting to home/dashboard or list. 
    // Assuming context, usually we want to go back to the list. 
    // Since 'AllCattle' seems to be the main list at route '/allCattles' (based on Sidebar), let's check sidebar again... 
    // Sidebar says: routerLink="/allCattles" for "Inventario Total". 
    this._router.navigate(['/allCattles']);
  }

}
