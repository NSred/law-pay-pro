import {Component, inject, OnInit} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-success',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage],
  templateUrl: './success.component.html',
  styleUrl: './success.component.scss'
})
export class SuccessComponent implements OnInit {
  private readonly route = inject(ActivatedRoute)
  public paymentId: string | null = ''
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.paymentId = params.get('paymentId');
    })
  }
}
