import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

type NavbarState = {
  isVisible: BehaviorSubject<boolean>
}

const initialState: NavbarState = {
  isVisible: new BehaviorSubject(true)
}

@Injectable({
  providedIn: 'root'
})
export class NavbarStateService {
  private state: NavbarState = initialState
  public isVisible$: Observable<boolean> = this.state.isVisible.asObservable();

  constructor() { }

  public updateNavbarVisibility(value: boolean) {
    this.state.isVisible.next(value)
  }
}
