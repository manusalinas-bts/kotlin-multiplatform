import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    @Environment(\.scenePhase) private var scenePhase
    @State var isDarkMode: Bool = UITraitCollection.current.userInterfaceStyle == .dark

    init() {
        MainViewControllerKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView(isDarkMode: $isDarkMode)
                .onChange(of: scenePhase) { oldValue, newValue in
                    if newValue == .active {
                        let osTheme: UITraitCollection = UIScreen.main.traitCollection
                        isDarkMode = osTheme.userInterfaceStyle == .dark
                    }
                }
                .preferredColorScheme(isDarkMode ? .dark : .light)
        }
    }
}
