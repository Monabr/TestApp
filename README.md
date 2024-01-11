# Case 1: Hilt tried to get ViewModel
Algorithm:

1. Press the first button
2. Wait for the bottom sheet to open completely
3. Close the bottom sheet
4. Wait for the bottom sheet to close completely
5. Click on the second button
6. Wait for the bottom sheet to open completely
7. Close the bottom sheet
8. Wait for the bottom sheet to close completely
9. Repeat the process from the beginning.

https://github.com/google/accompanist/assets/30532666/61f31fe8-a998-4b48-8624-d5b1e8a9e750

--------------------------------------

# Case 2: NavHost tried to navController.setViewModelStore(viewModelStoreOwner.viewModelStore)

Algorithm:
1. Press the "Menu" button.
2. Go to another screen by clicking on the button in the side menu that opens.
3. Go back by clicking on the "Arrow" button and immediately click on the "Menu" button.

https://github.com/google/accompanist/assets/30532666/6fb6e660-ec60-4c57-b6ff-78b0a4735e79

# Case 3: The problem with scroll of HorizontalPager with LazyVerticalGrid

Algorithm:
1. Open app with HorizontalPager with LazyVerticalGrid. The first one is parent, the second one is child.
2. Hold your finger on the LazyVerticalGrid and try quick scroll vertically up and down remember how it goes.
3. Scroll horizontally HorizontalPager to -1 page.
4. Scroll back horizontally HorizontalPager to 0 page.
5. Hold or finger on the LazyVerticalGrid and try quick scroll vertically up and down see how it's different - there is no scroll at all. (Please note: I am actively scrolling vertically in this video! I'm not waiting!)
6. Try scroll as usual up and down. See there is no more scroll.
7. Try small scrolls up and down. See that there some scroll left.

https://github.com/Monabr/TestApp/assets/30532666/3a16a1f2-0c25-4fd2-bd46-cb8672b754e9

