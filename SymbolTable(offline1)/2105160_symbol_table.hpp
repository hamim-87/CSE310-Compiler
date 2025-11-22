#pragma once

#include <string>
#include "2105160_scope_table.hpp"


class SymbolTable{
    private:
        ScopeTable * currentScope;
        int bucketNumberInScope;

        string hash;

        int collison;
        
    
    public:
        int nextScopeId;
        SymbolTable(int bucketNumberInScope = 13, string hash = "SDBM") noexcept;
        ~SymbolTable() noexcept;

        void EnterScope() noexcept;
        bool ExitScope(bool flag = false) noexcept;
        bool Insert(string symbolName,string symbolType) const noexcept;
        bool Remove(string symbolName) const noexcept;
        SymbolInfo* LookUp(string symbolName) const noexcept;
        void PrintCurrentScopeTable() noexcept;
        void PrintAllScopeTable() noexcept;

        int getAvgCollision() noexcept;


};