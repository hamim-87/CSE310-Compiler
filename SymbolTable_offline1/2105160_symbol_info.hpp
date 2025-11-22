#pragma once

#include <string>
using namespace std;

class SymbolInfo
{   
    private:
        string symbolName;
        string symbolType;
        SymbolInfo * nextSymbolInfo;

    public:

        SymbolInfo(const string& _symbolName, const string& _symbolType, SymbolInfo* _nextSymbolInfo = nullptr) noexcept;
        ~SymbolInfo() noexcept;

        void setSymbolName(const string& _symbolName) noexcept;
        [[nodiscard]] string getSymbolName() const noexcept;

        void setSymbolType(const string& _symbolType) noexcept;
        [[nodiscard]] string getSymbolType() const noexcept;

        void setNextSymbolInfo(SymbolInfo *_nextSymbolInfo) noexcept;
        [[nodiscard]] SymbolInfo * getNextSymbolInfo() const noexcept;

};