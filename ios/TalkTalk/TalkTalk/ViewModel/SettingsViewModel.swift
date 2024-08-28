//
//  SettingsViewModel.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import Foundation

class SettingsViewModel : ObservableObject {
    private var _userDefaultsRepository: UserDefaultsRepository = UserDefaultsRepository()
    
    @Published var isDarkTheme: Bool
    
    init () {
        isDarkTheme = _userDefaultsRepository.getBool(forKey: UserDefaultsKeys.DARK_THEME)
    }
    
    func saveTheme() -> Void {
        _userDefaultsRepository.save(
            isDarkTheme,
            forKey: UserDefaultsKeys.DARK_THEME
        )
    }
    
    func clearUserDefaults() {
        _userDefaultsRepository.clear()
    }
}
