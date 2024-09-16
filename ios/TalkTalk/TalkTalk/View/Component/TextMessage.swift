//
//  TextMessage.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 06/09/2024.
//

import SwiftUI

struct TextMessage: View {
    @AppStorage(UserDefaultsKeys.DARK_THEME) private var _darkTheme: Bool = false
    
    let message: String
    let date: Date
    let reversed: Bool
    
    var body: some View {
        HStack {
            if reversed {
                Spacer()
            }
            
            Text("\(message) \(date.messageDate())")
                .padding(.all, 5)
                .foregroundStyle(_darkTheme ? .black : .white)
                .background(reversed ? .primary : .secondary)
                .clipShape(Capsule())
            
            if !reversed {
                Spacer()
            }
        }.frame(maxWidth: .infinity)
            .padding(1)
    }
}

#Preview {
    TextMessage(
        message: "Hi!",
        date: Date(),
        reversed: true
    )
}
