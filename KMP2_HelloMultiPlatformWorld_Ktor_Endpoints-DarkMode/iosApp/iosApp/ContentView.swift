import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    @Binding var isDarkMode: Bool

    var body: some View {
        ZStack {
            ColorStatusBarView(isDarkMode: $isDarkMode)
            ComposeView()
        }
        .ignoresSafeArea(.all, edges: .bottom)
    }
}

// MARK: - Color Status Bar
struct ColorStatusBarView: View  {
    @Binding var isDarkMode: Bool

    var body: some View {
        let coloStatusBarDarkMode = Color.black.opacity(0.3)
        let statusBarColor = isDarkMode ? coloStatusBarDarkMode : .white
        return statusBarColor.ignoresSafeArea(.all)
    }
}

