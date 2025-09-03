# Bottom Navigation Implementation Plan

## Overview
Implement a bottom navigation bar with 4 main tabs for the Ali2BD shopping app:
- Home
- Category  
- Cart
- Account

## Components to Create

### 1. Navigation Structure
- **BottomNavigation.kt** - Main bottom navigation composable
- **MainNavigationItem.kt** - Data class for navigation items
- **MainNavigationDestinations.kt** - Navigation destinations enum/sealed class

### 2. Screen Components
- **HomeScreen.kt** - Main home screen with products/deals
- **CategoryScreen.kt** - Product categories browsing
- **CartScreen.kt** - Shopping cart management
- **AccountScreen.kt** - User profile and settings

### 3. Icons and Assets
- Use Material Icons for consistent UI:
  - Home: `Icons.Default.Home` / `Icons.Outlined.Home`
  - Category: `Icons.Default.Category` / `Icons.Outlined.Category`
  - Cart: `Icons.Default.ShoppingCart` / `Icons.Outlined.ShoppingCart`
  - Account: `Icons.Default.Person` / `Icons.Outlined.Person`

### 4. Animation Features
- **Click Animation**: Scale/ripple effect on tab selection
- **Icon Animation**: Smooth transition between filled/outlined icons
- **Badge Animation**: For cart item count
- **Smooth Transitions**: Between selected/unselected states

## Implementation Steps

### Phase 1: Basic Structure
1. Create navigation destinations sealed class
2. Create navigation item data class with icon states
3. Implement basic screen placeholders
4. Set up bottom navigation composable

### Phase 2: Animation Components
1. Create AnimatedBottomNavItem with scale animations
2. Add color transition animations using `animateColorAsState`
3. Implement icon switching between filled/outlined states
4. Add spring animations for smooth interactions

### Phase 3: Screen Implementation
1. Build HomeScreen with product grid/list
2. Create CategoryScreen with category navigation
3. Implement CartScreen with item management
4. Design AccountScreen with user profile options

### Phase 4: Advanced Features
1. Badge system for cart notifications
2. State management across navigation
3. Deep linking support
4. Accessibility improvements

## File Structure
```
main/
├── BottomNavigationPlan.md
├── MainNav.kt (existing - to be updated)
├── navigation/
│   ├── BottomNavigation.kt
│   ├── MainNavigationItem.kt
│   └── MainNavigationDestinations.kt
└── components/
    └── AnimatedBottomNavItem.kt
home/
├── HomeScreen.kt
category/
├── CategoryScreen.kt
Cart/
├── CartScreen.kt
Account/
├── AccountScreen.kt


```

## Technical Requirements
- Use Jetpack Compose Navigation
- Material 3 Design System
- Kotlin Multiplatform support (Android/iOS)
- State management with remember/rememberSaveable
- Proper accessibility support

## Animation Specifications
- **Click Animation**: 
  - Scale: 0.95f to 1.0f with spring animation
  - Duration: Spring with dampingRatio = 0.4f, stiffness = 1000f
- **Color Transition**: 
  - Primary color for selected state
  - OnSurfaceVariant for unselected state
  - Spring animation with dampingRatio = 0.8f
- **Icon Switch**: 
  - Crossfade between filled/outlined icons
  - Smooth color interpolation
- **Badge Animation**: 
  - Spring bounce for cart count updates
  - Error color scheme for cart badge

## Navigation Item Configuration
```kotlin
MainNavigationItem(
    destination: MainNavigationDestinations,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    label: String,
    hasNotification: Boolean = false,
    notificationCount: Int = 0
)
```

## Screen Features

### HomeScreen
- empty page wiill be added later

### CategoryScreen  
- empty page wiill be added later

### CartScreen
- empty page wiill be added later


### AccountScreen
- empty page wiill be added later

