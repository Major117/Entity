import {AfterViewInit, Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {HasElementRef} from "@angular/material/core/common-behaviors/color";
import {TokenStorageService} from "../services/token-storage.service";


@Component({
  selector: 'app-header-nav-bar',
  templateUrl: './header-nav-bar.component.html',
  styleUrls: ['./header-nav-bar.component.css']
})
export class HeaderNavBarComponent implements OnInit, AfterViewInit {

  @ViewChild ('stickyMenu') menuElement: HasElementRef;

  sticky: boolean = false;
  menuPosition : any;


  constructor(public tokenStorage: TokenStorageService) { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.menuPosition = this.menuElement._elementRef.nativeElement.offsetTop;
  }

  @HostListener ('window: scroll', ['$ event'])
  handleScroll() {
    const windowScroll = window.pageYOffset;

    if (windowScroll >= this.menuPosition) {
      this.sticky = true;
    } else {
      this.sticky = false;
    }
  }

}
