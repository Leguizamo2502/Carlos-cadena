import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterbookComponent } from './filterbook.component';

describe('FilterbookComponent', () => {
  let component: FilterbookComponent;
  let fixture: ComponentFixture<FilterbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilterbookComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilterbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
