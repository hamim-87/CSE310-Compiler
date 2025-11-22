#pragma once

#include <string>
#include <vector>
using namespace std;

class FunctionInfo {
public:
    string returnType;
    vector<pair<string, string>> parameters; 
    bool isDefined;

    FunctionInfo(const string& returnType, 
                 const vector<pair<string, string>>& params,
                 bool defined = false)
        : returnType(returnType), parameters(params), isDefined(defined) {}
};

class SymbolInfo
{   
    private:
        string symbolName;
        string symbolType;
        SymbolInfo * nextSymbolInfo;
        
    public:
        FunctionInfo* functionInfo;
        SymbolInfo(const string& _symbolName, const string& _symbolType, SymbolInfo* _nextSymbolInfo = nullptr,FunctionInfo* _functionInfo = nullptr) noexcept;
        ~SymbolInfo() noexcept;

        void setSymbolName(const string& _symbolName) noexcept;
        [[nodiscard]] string getSymbolName() const noexcept;

        void setSymbolType(const string& _symbolType) noexcept;
        [[nodiscard]] string getSymbolType() const noexcept;

        void setNextSymbolInfo(SymbolInfo *_nextSymbolInfo) noexcept;
        [[nodiscard]] SymbolInfo * getNextSymbolInfo() const noexcept;

};