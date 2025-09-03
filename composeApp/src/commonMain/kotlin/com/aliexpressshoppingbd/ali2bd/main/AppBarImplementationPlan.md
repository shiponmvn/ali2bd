# App Bar Implementation Plan for Ali2BD - Updated Design

## Overview
Create a modern app bar for the Ali2BD shopping app matching the orange-themed design with integrated search functionality, wishlist, and notifications.

## Design Specifications from Image

### Visual Design
- **Background**: Orange gradient (#FF6B35 to #FF4500)
- **Layout**: Custom layout (not standard Material3 TopAppBar)
- **Height**: Extended height to accommodate logo, tagline, and search bar
- **Text Colors**: White for all text elements

### Layout Structure
```
┌─────────────────────────────────────────────┐
│ Ali2BD                    [♡] [🔔]          │
│ Get Global Products At The Best Prices      │
│ ┌─────────────────────────────────────────┐ │
│ │ 🔍 Search from million product or paste │ │
│ └─────────────────────────────────────────┘ │
└─────────────────────────────────────────────┘
```

### Components
1. **Left Side**: 
   - "Ali2BD" logo/brand name (larger font)
   - Tagline below (smaller font)

2. **Right Side**:
   - Heart icon (wishlist/favorites)
   - Notification bell icon with badge

3. **Bottom Section**:
   - Full-width search bar
   - Search icon inside the field
   - Placeholder: "Search from million product or paste link"

## Implementation Changes Required

### 1. MainAppBar.kt - Complete Redesign
- Remove Material3 CenterAlignedTopAppBar
- Create custom Column layout
- Add orange gradient background
- Implement three-row layout:
  - Row 1: Logo + Icons
  - Row 2: Tagline
  - Row 3: Search bar

### 2. Color System Updates
- Add orange brand colors to theme
- Use white text throughout the app bar
- Ensure proper contrast ratios

### 3. New Components Needed
- **WishlistIcon**: Heart icon for favorites
- **CustomSearchField**: Integrated search field (not expandable)
- **GradientBackground**: Orange gradient background

### 4. Updated File Structure
```
main/
├── components/
│   ├── MainAppBar.kt (completely redesigned)
│   ├── WishlistIcon.kt (new)
│   ├── CustomSearchField.kt (new)
│   ├── NotificationIcon.kt (updated colors)
│   └── GradientBackground.kt (new)
```

## Technical Implementation

### App Bar Layout (New)
```
Column {
    Row { // Logo and Icons
        Text("Ali2BD") + Spacer + Heart + Notification
    }
    Text("Get Global Products At The Best Prices") // Tagline
    CustomSearchField() // Integrated search
}
```

### Styling Updates
- **Background**: Linear gradient orange
- **Text**: All white (#FFFFFF)
- **Icons**: White with proper opacity
- **Search Field**: White background with rounded corners
- **Padding**: Generous padding for better visual hierarchy

## Behavior Changes
- **No Expandable Search**: Search is always visible
- **Fixed Height**: App bar has consistent height
- **No Collapse**: App bar remains visible during scroll
- **Integration**: Seamlessly integrates with bottom navigation
