module.exports = {
    // Usa o preset de regras do Conventional Commits
    extends: ['@commitlint/config-conventional'],
    rules: {
        // Adicione regras customizadas, se necessário. Exemplo:
        // 'type-enum': [2, 'always', ['feat', 'fix', 'docs', 'style', 'refactor', 'test', 'chore', 'perf', 'ci']]
    }
};